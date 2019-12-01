package com.timmyg.doodlz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class EraseImageDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.message_erase);

        builder.setPositiveButton(R.string.button_erase, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getDoodleFragment().getDoodleView().clear();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
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

}
