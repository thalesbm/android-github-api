package br.tbm.github.api.app.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import br.tbm.github.api.R;
import br.tbm.github.api.shared.ui.activities.BaseActivity;
import br.tbm.github.api.shared.utils.RedirectUtils;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class SplashActivity extends BaseActivity implements
        Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(this, 2000);

        this.init();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        this.startAnimations();
    }

    /**
     * Metodo responsavel por initializar a animacao
     */
    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();

        LinearLayout llSplash = findViewById(R.id.activity_splashscreen_main_layout);
        llSplash.clearAnimation();
        llSplash.startAnimation(anim);

        Animation animFadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        ImageView ivLogo = findViewById(R.id.activity_splashscreen_logo_imageview);
        ivLogo.clearAnimation();
        ivLogo.startAnimation(animFadein);
    }

    @Override
    public void run() {
        RedirectUtils.redirectToMainActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}