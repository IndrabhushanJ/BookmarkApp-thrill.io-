package com.indrabhushan.BookmarkApp.bgjobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.indrabhushan.BookmarkApp.dao.BookmarkDao;
import com.indrabhushan.BookmarkApp.entities.WebLink;
import com.indrabhushan.BookmarkApp.entities.WebLink.DownloadStatus;
import com.indrabhushan.BookmarkApp.util.HttpConnect;
import com.indrabhushan.BookmarkApp.util.IOUtil;

public class WebPageDownloaderTask implements Runnable {

	private static BookmarkDao dao = new BookmarkDao();

	private static final long TIME_FRAME = 3000000000L;

	private boolean downloadAll = false;

	ExecutorService downloadExecutor = Executors.newFixedThreadPool(5);

	public WebPageDownloaderTask(boolean downloadAll) {
		this.downloadAll = downloadAll;
	}

	private static class Downloader<T extends WebLink> implements Callable<T> {
		private T weblink;

		public Downloader(T weblink) {
			this.weblink = weblink;
		}

		public T call() {
			try {
				if (!weblink.getUrl().endsWith(".pdf")) {
					weblink.setDownloadStatus(WebLink.DownloadStatus.FAILDED);
					String htmlPage = HttpConnect.download(weblink.getUrl());
					weblink.setHtmlPage(htmlPage);
				} else {
					weblink.setDownloadStatus(WebLink.DownloadStatus.NOT_ELIGIBLE);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return weblink;
		}
	}

	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {
			// Get Weblinks
			List<WebLink> weblinks = getWebLink();

			// download concurrently
			if (weblinks.size() > 0) {
				downlaod(weblinks);
			} else {
				System.out.println("No new Web Links  to download");
			}

			// wait
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		downloadExecutor.shutdown();

	}

	private void downlaod(List<WebLink> weblinks) {
		List<Downloader<WebLink>> tasks = getTasks(weblinks);
		List<Future<WebLink>> futures = new ArrayList<>();

		try {
			futures = downloadExecutor.invokeAll(tasks, TIME_FRAME, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Future<WebLink> future : futures) {
			try {
				if (!future.isCancelled()) {
					WebLink webLink = future.get();
					String webPage = webLink.getHtmlPage();
					if (webPage != null) {
						IOUtil.write(webPage, webLink.getId());
						webLink.setDownloadStatus(WebLink.DownloadStatus.SUCCESS);
						System.out.println("Download Success: " + webLink.getUrl());
					} else {
						System.out.println("Webpage not downloaded: " + webLink.getUrl());
					}
				} else {
					System.out.println("\n\nTask is cancelled --> " + Thread.currentThread());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

	}

	private List<Downloader<WebLink>> getTasks(List<WebLink> weblinks) {
		List<Downloader<WebLink>> tasks = new ArrayList<>();

		for (WebLink weblink : weblinks) {
			tasks.add(new Downloader<WebLink>(weblink));
		}
		return tasks;
	}

	private List<WebLink> getWebLink() {
		List<WebLink> weblinks = null;

		if (downloadAll) {
			weblinks = dao.getAllWebLinks();
			downloadAll = false;
		} else {
			weblinks = dao.getWebLinks(WebLink.DownloadStatus.NOT_ATTEMPTED);
		}
		return weblinks;
	}

}
