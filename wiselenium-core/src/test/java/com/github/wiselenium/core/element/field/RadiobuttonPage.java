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
package com.github.wiselenium.core.element.field;

import static com.github.wiselenium.core.util.TestResourceFinder.getAbsolutePath;
import static org.testng.Assert.assertEquals;


import com.github.wiselenium.core.element.field.Radiobutton;
import com.github.wiselenium.core.element.field.impl.RadiobuttonImpl;
import com.github.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class RadiobuttonPage extends Page<RadiobuttonPage> {
	
	public static final String URL = getAbsolutePath("radiobutton.html");
	public static final String TITLE = "page for radiobutton tests";
	
	private Radiobutton radiobutton;
	private Radiobutton disabledRadiobutton;
	private Radiobutton checkedRadiobutton;
	private Radiobutton checkedCheckedRadiobutton;
	private Radiobutton checkedTrueRadiobutton;
	private RadiobuttonImpl checkedFalseRadiobutton;
	
	
	public Radiobutton getCheckedCheckedRadiobutton() {
		return this.checkedCheckedRadiobutton;
	}
	
	public Radiobutton getCheckedFalseRadiobutton() {
		return this.checkedFalseRadiobutton;
	}
	
	public Radiobutton getCheckedRadiobutton() {
		return this.checkedRadiobutton;
	}
	
	public Radiobutton getCheckedTrueRadiobutton() {
		return this.checkedTrueRadiobutton;
	}
	
	public Radiobutton getDisabledRadiobutton() {
		return this.disabledRadiobutton;
	}
	
	public Radiobutton getRadiobutton() {
		return this.radiobutton;
	}
	
	@Override
	protected void isLoaded() {
		assertEquals(this.getTitle(), TITLE);
		assertEquals(this.getWrappedDriver().getCurrentUrl(), URL);
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
