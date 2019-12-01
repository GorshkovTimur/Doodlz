package com.timmyg.doodlz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.timmyg.doodlz.View.DoodleView;

public class ColorDialogFragment extends DialogFragment {

    private SeekBar alphaSeekbar;
    private SeekBar redSeekbar;
    private SeekBar greenSeekbar;
    private SeekBar blueSeekbar;
    private View colorView;
    private int color;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View colorDialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_color, null);
        builder.setView(colorDialogView);

        alphaSeekbar = colorDialogView.findViewById(R.id.alphaSeekBar);
        redSeekbar = colorDialogView.findViewById(R.id.redSeekBar);
        greenSeekbar = colorDialogView.findViewById(R.id.greenSeekBar);
        blueSeekbar = colorDialogView.findViewById(R.id.blueSeekBar);

        colorView = colorDialogView.findViewById(R.id.colorView);

        alphaSeekbar.setOnSeekBarChangeListener(colorChangedListener);
        redSeekbar.setOnSeekBarChangeListener(colorChangedListener);
        greenSeekbar.setOnSeekBarChangeListener(colorChangedListener);
        blueSeekbar.setOnSeekBarChangeListener(colorChangedListener);

        final DoodleView doodleView = getDoodleFragment().getDoodleView();
        color = doodleView.getDrawingColor();
        alphaSeekbar.setProgress(Color.alpha(color));
        redSeekbar.setProgress(Color.red(color));
        greenSeekbar.setProgress(Color.green(color));
        blueSeekbar.setProgress(Color.blue(color));

        builder.setPositiveButton(R.string.button_set_color, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doodleView.setDrawingColor(color);
            }
        });
        return builder.create();
    }

    private Main2ActivityFragment getDoodleFragment() {
        return (Main2ActivityFragment) getFragmentManager().findFragmentById(R.id.doodle_fragment);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Main2ActivityFragment fragment = getDoodleFragment();

        if (fragment != null) {
            fragment.setShowsDialog(true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Main2ActivityFragment fragment = getDoodleFragment();

        if (fragment != null) {
            fragment.setShowsDialog(false);
        }
    }

    private final SeekBar.OnSeekBarChangeListener colorChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (b){
                color = Color.argb(alphaSeekbar.getProgress(), redSeekbar.getProgress(),
                        greenSeekbar.getProgress(), blueSeekbar.getProgress());
                colorView.setBackgroundColor(color);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
