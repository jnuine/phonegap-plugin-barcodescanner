package com.jnuine.cordova.barcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CaptureActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent intent = new Intent("com.jnuine.cordova.barcodescanner.CAPTURE_RESULT");
        intent.putExtra("SCAN_RESULT", rawResult.getText());
        intent.putExtra("SCAN_RESULT_FORMAT", rawResult.getBarcodeFormat().toString());

        setResult(Activity.RESULT_OK, intent);

        mScannerView.stopCamera();
        this.finish();
    }
}
