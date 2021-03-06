package apidez.com.firebase.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import apidez.com.firebase.R;
import apidez.com.firebase.model.Priority;

/**
 * Created by nongdenchet on 2/11/16.
 */
public class PriorityPicker extends LinearLayout {
    private PriorityView mHighPriorityView, mMediumPriorityView, mLowPriorityView;
    private PriorityView[] mPriorityViews;
    private Priority mPriority = Priority.HIGH;
    private Listener mListener;

    public interface Listener {
        void onPriorityChange(Priority priority);
    }

    public PriorityPicker(Context context) {
        super(context);
        initialize();
    }

    public PriorityPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public PriorityPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    private void initialize() {
        inflate(getContext(), R.layout.priority_picker, this);
        initViews();
        initPriorities();
        initActions();
    }

    public Priority getPriority() {
        return mPriority;
    }

    private void initViews() {
        mHighPriorityView = (PriorityView) findViewById(R.id.priority_high);
        mMediumPriorityView = (PriorityView) findViewById(R.id.priority_medium);
        mLowPriorityView = (PriorityView) findViewById(R.id.priority_low);
        mPriorityViews = new PriorityView[]{mLowPriorityView, mMediumPriorityView, mHighPriorityView};
    }

    private void initPriorities() {
        mHighPriorityView.setPriority(Priority.HIGH);
        mMediumPriorityView.setPriority(Priority.MED);
        mLowPriorityView.setPriority(Priority.LOW);
    }

    private void initActions() {
        for (PriorityView priorityView : mPriorityViews) {
            priorityView.setOnClickListener(v -> selectPriority(priorityView));
        }
        selectDefaultPriority();
    }

    public void setPriority(Priority priority) {
        selectPriority(mPriorityViews[priority.ordinal()]);
    }

    private void selectDefaultPriority() {
        selectPriority(mHighPriorityView);
    }

    private void selectPriority(PriorityView priorityView) {
        unSelectAllPriorityViews();
        priorityView.select();
        if (mListener != null) {
            mListener.onPriorityChange(priorityView.getPriority());
        }
        mPriority = priorityView.getPriority();
    }

    private void unSelectAllPriorityViews() {
        for (PriorityView priorityView : mPriorityViews) {
            priorityView.unSelect();
        }
    }
}
