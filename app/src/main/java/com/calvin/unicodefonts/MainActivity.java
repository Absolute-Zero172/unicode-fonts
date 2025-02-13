package com.calvin.unicodefonts;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public int getVariance() {
        return ((SeekBar) findViewById(R.id.varianceSlider)).getProgress();
    }

    public int getNumber() {
        return ((SeekBar) findViewById(R.id.numberSlider)).getProgress();
    }

    public void click(View v) {
        Log.d("clicked button", v.getResources().getResourceName(v.getId()));

        MaterialSwitch clipboardSwitch = (findViewById(R.id.clipboardToggle));
        boolean useClipboard = clipboardSwitch.isChecked();

        EditText textEnter = findViewById(R.id.textEnter);
        String inputText;
        String result;

        if (useClipboard) {
            Log.d("using clipboard", "switch toggle indicates clipboard usage");

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

            if (!clipboardManager.hasPrimaryClip()) {
                Toast.makeText(this, "No Primary Clip", Toast.LENGTH_SHORT).show();
                return;
            }

            inputText = Objects.requireNonNull(clipboardManager.getPrimaryClip()).getItemAt(0).getText().toString();

        } else {
            inputText = textEnter.getText().toString();
        }


        // font checks
        if (v.getId() == R.id.bold) {
            result = Font.applyFont(inputText, getString(R.string.bold_font));
        } else if (v.getId() == R.id.italics) {
            result = Font.applyFont(inputText, getString(R.string.italics_font));
        } else if (v.getId() == R.id.bold_italics) {
            result = Font.applyFont(inputText, getString(R.string.bold_italics_font));
        } else if (v.getId() == R.id.gothic) {
            result = Font.applyFont(inputText, getString(R.string.gothic_font));
        } else if (v.getId() == R.id.gothic_bold) {
            result = Font.applyFont(inputText, getString(R.string.gothic_bold_font));
        } else if (v.getId() == R.id.strikethrough) {
            result = Font.applyFont(inputText, getString(R.string.strikethrough_font));
        } else if (v.getId() == R.id.cursive) {
            result = Font.applyFont(inputText, getString(R.string.cursive_font));
        } else if (v.getId() == R.id.cursive_bold) {
            result = Font.applyFont(inputText, getString(R.string.cursive_bold_font));
        } else if (v.getId() == R.id.underline) {
            result = Font.applyFont(inputText, getString(R.string.underline_font));
        } else if (v.getId() == R.id.block) {
            result = Font.applyFont(inputText, getString(R.string.block_font));
        } else if (v.getId() == R.id.circle) {
            result = Font.applyFont(inputText, getString(R.string.circle_font));
        } else if (v.getId() == R.id.square) {
            result = Font.applyFont(inputText, getString(R.string.square_font));
        } else if (v.getId() == R.id.subscript) {
            result = Font.applyFont(inputText, getString(R.string.subscript_font));
        } else if (v.getId() == R.id.superscript) {
            result = Font.applyFont(inputText, getString(R.string.superscript_font));
        } else if (v.getId() == R.id.squiggle) {
            result = Font.applyFont(inputText, getString(R.string.squiggle_font));
        } else if (v.getId() == R.id.upside_down) {
            result = Font.applyFont(inputText, getString(R.string.upside_down_font));
        } else if (v.getId() == R.id.monospace) {
            result = Font.applyFont(inputText, getString(R.string.monospace_font));
        }

        // algorithm string manipulation

        else if (v.getId() == R.id.script_alternate) {
            result = Font.scriptAlternate(inputText, getString(R.string.superscript_font), getString(R.string.subscript_font));
        } else if (v.getId() == R.id.spaces) {
            result = Font.addSpaces(inputText, getNumber(), getVariance());
        } else if (v.getId() == R.id.periods) {
            result = Font.addPeriods(inputText);
        } else if (v.getId() == R.id.sparkles) {
            result = Font.addSparkles(inputText, getNumber(), getVariance());
        } else if (v.getId() == R.id.letters) {
            result = Font.addLetters(inputText, getNumber(), getVariance());
        } else if (v.getId() == R.id.capitals) {
            result = Font.capitals(inputText, getNumber(), getVariance());
        } else if (v.getId() == R.id.uwu) {
            result = Font.uwu(inputText);
        }

        // default
        else {
            result = "Button Not Yet Implemented";
        }

        textEnter.setText(result);

        if (useClipboard) {
            ClipData resultToCopy = ClipData.newPlainText("unicode_font_applied", result);
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(resultToCopy);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // initialize variance seekbar
        SeekBar varianceSlider = findViewById(R.id.varianceSlider);
        ((TextView) findViewById(R.id.varianceView)).setText(R.string.initial_variance);
        varianceSlider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        ((TextView) findViewById(R.id.varianceView)).setText(String.format(getString(R.string.variance_template), progress));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        // initialize number seekbar
        SeekBar numberSlider = findViewById(R.id.numberSlider);
        ((TextView) findViewById(R.id.numberView)).setText(R.string.initial_number);
        numberSlider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        ((TextView) findViewById(R.id.numberView)).setText(String.format(getString(R.string.number_template), progress));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        // initialize clipboard toggle to true
        ((MaterialSwitch)findViewById(R.id.clipboardToggle)).setChecked(true);

    }
}