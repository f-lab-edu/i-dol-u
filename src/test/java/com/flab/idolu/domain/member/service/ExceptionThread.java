package com.flab.idolu.domain.member.service;

public class ExceptionThread {

	private Thread thread;
	private RuntimeException exception;

	/**
	 * ExceptionThread 스레드에서 발생한 예외를 생성한 스레드에서 캐치하고 처리 가능
	 * @param runnable
	 */
	public ExceptionThread(Runnable runnable) {
		this.thread = new Thread(() -> {
			try {
				runnable.run();
			} catch (RuntimeException e) {
				exception = e;
			}
		});
	}

	public void start() {
		thread.start();
	}

	public void join() throws InterruptedException {
		thread.join();
	}

	public void checkException() {
		if (exception != null) {
			throw exception;
		}
	}
}
