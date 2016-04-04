package net.java.html.lib.jquery;

import net.java.html.lib.dom.Document;
import org.junit.Assert;
import org.junit.Test;
import static net.java.html.lib.dom.Exports.window;
import net.java.html.lib.dom.HTMLElement;
import net.java.html.junit.BrowserRunner;
import net.java.html.junit.HTMLContent;
import org.junit.runner.RunWith;
import static net.java.html.lib.jquery.Exports.$;

/*
 * #%L
 * DukeScript Libraries Processor - a library from the DukeScript project.
 * Visit http://dukescript.com for support and commercial license.
 * %%
 * Copyright (C) 2015 Eppleton IT Consulting
 * %%
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
 * #L%
 */

@RunWith(BrowserRunner.class)
@HTMLContent("<span id='text'>Nothing yet</span>")
public class JQueryTest {
    String assertText(String msg) {
        final Document doc = window.document();
        final HTMLElement element = doc.getElementById("text");
        String txt = element.innerHTML();
        if (msg == null) {
            return txt;
        }
        Assert.assertEquals(msg, txt);
        return txt;
    }

    @Test
    public void simpleJQuerySelector() throws Exception {
        final String message = "Hello from JQuery!";
        $("#text").text(message);
        assertText(message);
    }
}
