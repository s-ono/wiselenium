/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.wiselenium.core.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.wiselenium.core.WiseRoot;
import com.github.wiselenium.core.util.ScreenShooter;

/**
 * Basic implementation of a common Page. Should be extended to reflect your own page services. <br/>
 * It extends {@link LoadableComponent}, so you can easily take advantage of its loading resources
 * just by overriding the empty load() and isLoaded() methods. <br/>
 * It has a constructor that takes a WebDriver as only argument and another no-arg constructor,
 * allowing you to extend and initialize it with the WisePageFactory with either constructor you
 * prefer.
 * 
 * @author Andre Ricardo Schaffer
 * @param <T> The page type.
 * @since 0.1.0
 */
public class Page<T extends Page<T>> extends LoadableComponent<T> implements WiseRoot<T>,
	WrapsDriver {
	
	private WebDriver driver;
	
	
	/**
	 */
	public Page() {}
	
	/**
	 * @param driver The driver to be encapsulated within the page.
	 */
	public Page(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Returns this page instance in order to allow chain calls in a more fluent way.
	 * 
	 * @return This page instance.
	 * @since 0.1.0
	 */
	@SuppressWarnings("unchecked")
	public T and() {
		return (T) this;
	}
	
	/**
	 * Executes a script on the page.
	 * 
	 * @param script The script to be executed.
	 * @return The result of the script.
	 * @since 0.1.0
	 */
	public Object executeScript(String script) {
		return ((JavascriptExecutor) this.getWrappedDriver()).executeScript(script);
	}
	
	@Override
	public <E> E findElement(Class<E> clazz, By by) {
		return WiseLocator.findElement(clazz, by, this.getWrappedDriver());
	}
	
	@Override
	public <E> List<E> findElements(Class<E> clazz, By by) {
		return WiseLocator.findElements(clazz, by, this.getWrappedDriver());
	}
	
	/**
	 * Loads a new web page in the current browser window using a HTTP GET operation.
	 * 
	 * @param url The URL to load. It is best to use a fully qualified URL.
	 * @return This page object.
	 * @since 0.1.0
	 */
	@SuppressWarnings("unchecked")
	public T get(String url) {
		this.getWrappedDriver().get(url);
		return (T) this;
	}
	
	/**
	 * Retrieves the current URL that the browser is looking at.
	 * 
	 * @return The current URL.
	 * @since 0.1.0
	 */
	public String getCurrentUrl() {
		return this.getWrappedDriver().getCurrentUrl();
	}
	
	/**
	 * Retrieves the source of the page rendered in the browser.
	 * 
	 * @return The source of the page.
	 * @since 0.1.0
	 */
	public String getPageSource() {
		return this.getWrappedDriver().getPageSource();
	}
	
	/**
	 * Retrieves the title of the current page.
	 * 
	 * @return The title of the current page, or null if one is not set.
	 * @since 0.1.0
	 */
	public String getTitle() {
		return this.getWrappedDriver().getTitle();
	}
	
	@Override
	public WebDriver getWrappedDriver() {
		return this.driver;
	}
	
	/**
	 * Instantiates the next page of the user navigation and initialize its elements.
	 * 
	 * @param <E> The type of the page.
	 * @param clazz The class of the page.
	 * @return An instance of the next page of the user navigation.
	 * @since 0.1.0
	 */
	public <E> E initNextPage(Class<E> clazz) {
		return WisePageFactory.initElements(this.getWrappedDriver(), clazz);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T takeScreenShot(String fileName) {
		ScreenShooter.takeScreenShot(this.getWrappedDriver(), fileName);
		return (T) this;
	}
	
	@Override
	public WebDriverWait waitFor(long timeOutInSeconds) {
		return new WebDriverWait(this.getWrappedDriver(), timeOutInSeconds);
	}
	
	@Override
	public WebDriverWait waitFor(long timeOutInSeconds, long sleepInMillis) {
		return new WebDriverWait(this.getWrappedDriver(), timeOutInSeconds, sleepInMillis);
	}
	
	@Override
	protected void isLoaded() {}
	
	@Override
	protected void load() {}
	
}
