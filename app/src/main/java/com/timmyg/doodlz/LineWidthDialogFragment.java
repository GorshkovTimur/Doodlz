package com.timmyg.doodlz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.timmyg.doodlz.View.DoodleView;

public class LineWidthDialogFragment extends DialogFragment {

    private ImageView widthImageView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View lineWidthView = getActivity().getLayoutInflater().inflate(R.layout.fragment_line_width,null);
        builder.setView(lineWidthView);
        builder.setTitle(R.string.title_line_width_dialog);

        widthImageView = lineWidthView.findViewById(R.id.width_imageview);

        final DoodleView doodleView = getDoodleFragment().getDoodleView();
        final SeekBar widthSeekbar = lineWidthView.findViewById(R.id.width_seekbar);
        widthSeekbar.setOnSeekBarChangeListener(lineWidthChanged);
        widthSeekbar.setProgress(doodleView.getLineWidth());

        builder.setPositiveButton(R.string.button_set_line_width,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        doodleView.setLineWidth(widthSeekbar.getProgress());
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

    private final SeekBar.OnSeekBarChangeListener lineWidthChanged = new SeekBar.OnSeekBarChangeListener() {
        final Bitmap bitmap = Bitmap.createBitmap(400, 100, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Paint p = new Paint();
        p.setColor(getDoodleFragment().getDoodleView().getDrawingColor());
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(i);

        bitmap.eraseColor(getResources().getColor(android.R.color.transparent, getContext().getTheme()));
        canvas.drawLine(30,50,370,50,p);
        widthImageView.setImageBitmap(bitmap);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
