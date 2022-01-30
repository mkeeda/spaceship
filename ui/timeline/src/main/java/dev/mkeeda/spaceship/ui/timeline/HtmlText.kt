package dev.mkeeda.spaceship.ui.timeline

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.HtmlCompat
import dev.mkeeda.spaceship.ui.common.util.PreviewBackground

@Composable
fun HtmlText(
    html: String,
    modifier: Modifier = Modifier
) {
    val htmlAnnotatedString = remember(html) {
        HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT).toAnnotatedString()
    }
    Text(text = htmlAnnotatedString, modifier = modifier)
}

private fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold),
                    start = start,
                    end = end
                )
                Typeface.ITALIC -> addStyle(
                    style = SpanStyle(fontStyle = FontStyle.Italic),
                    start = start,
                    end = end
                )
                Typeface.BOLD_ITALIC -> addStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    ),
                    start = start,
                    end = end
                )
            }
            is UnderlineSpan -> addStyle(
                style = SpanStyle(textDecoration = TextDecoration.Underline),
                start = start,
                end = end
            )
            is ForegroundColorSpan -> addStyle(
                style = SpanStyle(color = Color(span.foregroundColor)),
                start = start,
                end = end
            )
        }
    }
}

@Preview
@Composable
private fun HtmlTextPreview() {
    PreviewBackground {
        HtmlText(
            html = """
           <div>テキスト<div><b>太字</b><br></div><div><i>イタリック<br></i></div><div><br></div><div><font size=\"6\">でかい</font></div><div><br></div><div><a class=\"ocean-ui-plugin-mention-user ocean-ui-plugin-linkbubble-no\" href=\"/k/#/people/user/user2\" data-mention-id=\"10\" rel=\"nofollow\">user2</a>&nbsp;メンション<br></div><div><br></div><div><font color=\"#ff0000\">赤いぞ</font></div></div> 
            """.trimIndent()
        )
    }
}
