package com.iteso.pdm18_scrollabletabs;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Carlos Rivera on 12/03/2018.
 */

public class WebAppInterface {
    Context context;

    public WebAppInterface(Context mcontext) {
        context = mcontext;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
