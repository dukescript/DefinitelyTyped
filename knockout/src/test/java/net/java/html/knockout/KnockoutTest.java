package net.java.html.knockout;

import net.java.html.js.JavaScriptBody;
import net.java.html.junit.BrowserRunner;
import net.java.html.junit.HTMLContent;
import net.java.html.lib.Objs;
import net.java.html.lib.Function;
import net.java.html.lib.knockout.KnockoutObservable;
import static net.java.html.lib.knockout.Exports.ko;
import static net.java.html.lib.dom.Exports.window;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
  #%L
  DukeScript Libraries - a library from the DukeScript project.
  Visit http://dukescript.com for support and commercial license.
  %%
  Copyright (C) 2016 Dukehoff GmbH
  %%
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
  #L%
  */

@RunWith(BrowserRunner.class)
@HTMLContent("<div data-bind=\"route: msg\">\n" +
"    <h1>Test</h1>\n" +
"    <ul>\n" +
"        <li><a href=\"#/home\" id=\"home\">Home</a></li>\n" +
"        <li><a href=\"#/page1\">Page 1</a></li>\n" +
"        <li><a href=\"#/page2\">Page 2</a></li>\n" +
"    </ul>\n" +
"    <span data-bind=\"text: msg\" id=\"text\">Not initialized</span>\n" +
"</div>")
public class KnockoutTest {
    void initializeRoute() {
        Objs routing = new Objs();
        class Init implements Function.A2<Object,Function.A0<Object>, Void> {
            @Override
            public Void call(Object p1, Function.A0<Object> p2) {
                Function.A0<Void> onHashChange = () -> {
                    String hash = window.location().hash().substring(2);
                    int at = hash.lastIndexOf("/");
                    String after = hash.substring(at + 1);
                    Function.A1<Object, ?> callback = (Function.A1<Object,?>) p2.call();
                    callback.call(after);
                    return null;
                };
                window.$set("onhashchange", onHashChange);
                return null;
            }
        }
        routing.$set("init", new Init());
        ko.bindingHandlers().$set("route", routing);
    }

    String assertText(String msg) {
        String txt = window.document().getElementById("text").innerHTML();
        if (msg == null) {
            return txt;
        }
        Assert.assertEquals(msg, txt);
        return txt;
    }

    @JavaScriptBody(args = {}, body =
          "var e = window.document.getElementById('home');\n "
        + "var ev = window.document.createEvent('MouseEvents');\n "
        + "ev.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);\n "
        + "e.dispatchEvent(ev);\n "
        + "if (!e.checked) {\n"
        + "  e.checked = true;\n "
        + "  e.dispatchEvent(ev);\n "
        + "}\n"
    )
    public native void triggerClick();
    
    @Test
    public Runnable[] initPresenter() throws Exception {
        initializeRoute();
        return new Runnable[] {
            () -> {
                Objs obj = new Objs();
                KnockoutObservable msg = ko.observable("It runs!");
                obj.$set("msg", msg);
                ko.applyBindings(obj);
                assertText("It runs!");
                msg.$apply("It runs even better!");
                assertText("It runs even better!");
            },
            () -> {
                triggerClick();
            },
            () -> {
                String t = assertText(null);
                if (!"home".equals(t)) {
                    fail("Wrong currentText: " + t);
                }
            }
        };
    }
}
