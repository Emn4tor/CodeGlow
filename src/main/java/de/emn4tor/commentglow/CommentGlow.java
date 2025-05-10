package de.emn4tor.commentglow;

/*
 *  @author: Emn4tor
 *  @created: 10.05.2025
 */

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.AnnotationHolder;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class CommentGlow implements Annotator {

    private static final TextAttributesKey BUG_KEY = TextAttributesKey.createTextAttributesKey("BUG_COMMENT", new TextAttributes(new Color(248, 155, 62), null, null, null, Font.PLAIN));
    private static final TextAttributesKey FIX_KEY = TextAttributesKey.createTextAttributesKey("FIX_COMMENT", new TextAttributes(new Color(248, 105, 62), null, null, null, Font.PLAIN));
    private static final TextAttributesKey NOTE_KEY = TextAttributesKey.createTextAttributesKey("NOTE_COMMENT", new TextAttributes(new Color(225, 109, 253), null, null, null, Font.PLAIN));
    private static final TextAttributesKey INFO_KEY = TextAttributesKey.createTextAttributesKey("INFO_COMMENT", new TextAttributes(new Color(255, 203, 31), null, null, null, Font.PLAIN));
    private static final TextAttributesKey WARNING_KEY = TextAttributesKey.createTextAttributesKey("WARNING_COMMENT", new TextAttributes(new Color(255, 79, 79), null, null, null, Font.PLAIN));
    private static final TextAttributesKey ERROR_KEY = TextAttributesKey.createTextAttributesKey("ERROR_COMMENT", new TextAttributes(new Color(170, 36, 51), null, null, null, Font.PLAIN));
    private static final TextAttributesKey SECURITY_KEY = TextAttributesKey.createTextAttributesKey("SECURITY_COMMENT", new TextAttributes(new Color(35, 179, 35), null, null, null, Font.PLAIN));

    HashMap <String, TextAttributesKey> colorMap = new HashMap<>();
    {
        colorMap.put("BUG", BUG_KEY);
        colorMap.put("FIX", FIX_KEY);
        colorMap.put("NOTE", NOTE_KEY);
        colorMap.put("INFO", INFO_KEY);
        colorMap.put("WARNING", WARNING_KEY);
        colorMap.put("ERROR", ERROR_KEY);
        colorMap.put("SECURITY", SECURITY_KEY);
        colorMap.put("SECURITY WARNING", SECURITY_KEY);
        colorMap.put("SECURITY NOTE", SECURITY_KEY);
    }

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof PsiComment)) return;

        String text = element.getText();


        if (colorMap.keySet().stream().anyMatch(k -> text.contains(k))) {
            colorMap.forEach((key, value) -> {
                if (text.contains(key)) {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(element)
                            .textAttributes(value)
                            .create();
                }
            });
        }
    }


}