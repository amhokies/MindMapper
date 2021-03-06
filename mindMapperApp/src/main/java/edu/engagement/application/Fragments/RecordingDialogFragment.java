package edu.engagement.application.Fragments;

import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import edu.engagement.application.AttentionLevel;
import edu.engagement.application.R;

/**
 * Created by IvenRee on 7/25/15.
 */
public class RecordingDialogFragment extends DialogFragment {

    private Button mSaveNote;
    private EditText mAnnotation;
    private Button mDone;
    private SeekBar mSeekBar;

    private AttentionLevel attentionLevel;

    //static RecordingDialogFragment newInstance(){
    //    return new RecordingDialogFragment();
    //}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dialogView = inflater.inflate(R.layout.recording_dialog, container, false);

        mSaveNote = (Button) dialogView.findViewById(R.id.saveNotes);
        mDone = (Button) dialogView.findViewById(R.id.doneButton);
        mAnnotation = (EditText) dialogView.findViewById(R.id.annotation);
        mSeekBar = (SeekBar) dialogView.findViewById(R.id.annotation_bar);

        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnnotation.getText().length() == 0){
                    Toast.makeText(getActivity(), "Please describe your experience!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //save annotation
                    //
                    Toast.makeText(getActivity(), "Your experience saved!", Toast.LENGTH_SHORT).show();
                    mAnnotation.setText("");
                    mSeekBar.setProgress(0);
                }
            }
        });

        //
        attentionLevel = AttentionLevel.LOW;
        mSeekBar = (SeekBar) dialogView.findViewById(R.id.annotation_bar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                attentionLevel = AttentionLevel.fromInt(progress);
                setProgressBarColor(seekBar, attentionLevel.getColor());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return dialogView;
    }

    private void setProgressBarColor(SeekBar seekBar, int newColor) {
        LayerDrawable ld = (LayerDrawable) seekBar.getProgressDrawable();
        ClipDrawable d1 = (ClipDrawable) ld.findDrawableByLayerId(R.id.progressShape);
        d1.setColorFilter(newColor, PorterDuff.Mode.SRC_IN);
    }

}
