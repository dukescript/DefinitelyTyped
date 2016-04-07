package net.java.html.lib.angular;

import net.java.html.lib.Function;
import org.junit.Assert;
import org.junit.Test;
import net.java.html.junit.BrowserRunner;
import net.java.html.junit.HTMLContent;
import static net.java.html.lib.angular.Exports.angular;
import static net.java.html.lib.jquery.Exports.$;
import net.java.html.lib.jquery.JQuery;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
@HTMLContent("\n" +
"<div ng-app='example' ng-strict-di ng-controller='SampleController'>\n" +
"  <h1 id='text'>Hello {{sometext}}!</h1>\n" +
"  <button id='change' ng-click='changetext()'>Enlarge!</button>\n" +
"</div>\n" +
"")
public class AngularTest {
    IScope scope;

    String assertText(String msg) {
        JQuery element = $("#text");
        String txt = element.text();
        if (msg == null) {
            return txt;
        }
        Assert.assertEquals(msg, txt);
        return txt;
    }

    @Test
    public Runnable[] simpleAngularModule() throws Exception {
        assertNotNull("Angular global found", angular);
        IModule module = angular.module("example", new String[0]);
        Function initFn = Function.newFunction((Function.A1<Object, Void>) (Object arg1) -> {
            scope = IScope.$as(arg1);
            scope.$set("sometext", "DukeScript");
            scope.$set("changetext", (Function.A0<Void>) () -> {
                scope.$set("sometext", "Great " + scope.$get("sometext"));
                return null;
            });
            return null;
        });
        net.java.html.lib.angular.Function.$as(initFn).$inject.call(new String[] { "$scope" });

        assertTrue("is function", angular.isFunction(initFn));
        module.controller("SampleController", new Object[] { "$scope", Function.$js(initFn) });

        return new Runnable[] {
            () -> assertText("Hello DukeScript!"),
            () -> {
                $("#change").click();
            },
            () -> {
                assertText("Hello Great DukeScript!");
            }
        };
    }
}
