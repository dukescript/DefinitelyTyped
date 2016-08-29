package net.java.html.lib.snapsvg;

import net.java.html.lib.dom.Document;
import org.junit.Assert;
import org.junit.Test;
import static net.java.html.lib.dom.Exports.window;
import net.java.html.lib.dom.HTMLElement;
import net.java.html.junit.BrowserRunner;
import net.java.html.junit.HTMLContent;
import net.java.html.lib.Objs;
import net.java.html.lib.snapsvg.Snap.Element;
import net.java.html.lib.snapsvg.Snap.Paper;
import org.junit.runner.RunWith;

/*
 * #%L
 * DukeScript Definitely Typed - a library from the DukeScript project.
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
@HTMLContent("\n" +
    "<svg id='demo' width='300' height='300'>\n" +
    "</svg>\n"
)
public class SnapSvgTest {
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
    public void drawSomethingSimple() throws Exception {
        Paper s = Exports.Snap("#demo");
        Element bigCircle = s.circle(150, 150, 100);
        bigCircle.attr(values(
            "fill", "#bada55",
            "stroke", "#000",
            "strokeWidth", 5
        ));

        Element smallCircle = s.circle(100, 150, 70);
        Element discs = Element.$as(s.group(smallCircle, s.circle(200, 150, 70)));
        discs.attr(values(
            "fill", "#fff"
        ));

        bigCircle.attr(values(
            "mask", discs
        ));

        smallCircle.animate(values("r", 50), 1000);
    }

    private static Objs values(Object... attrs) {
        Objs js = new Objs();
        for (int i = 0; i < attrs.length; i += 2) {
            js.$set((String) attrs[i], attrs[i + 1]);
        }
        return js;
    }
}
