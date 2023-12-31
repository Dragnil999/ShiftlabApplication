package com.example.shiftlabapplication.features.join.databinding;
import com.example.shiftlabapplication.features.join.R;
import com.example.shiftlabapplication.features.join.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentJoinBindingImpl extends FragmentJoinBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.name_input_layout, 1);
        sViewsWithIds.put(R.id.name_field, 2);
        sViewsWithIds.put(R.id.surname_input_layout, 3);
        sViewsWithIds.put(R.id.surname_field, 4);
        sViewsWithIds.put(R.id.birth_date_input_layout, 5);
        sViewsWithIds.put(R.id.birth_date_field, 6);
        sViewsWithIds.put(R.id.password_input_layout, 7);
        sViewsWithIds.put(R.id.password_field, 8);
        sViewsWithIds.put(R.id.repeat_password_input_layout, 9);
        sViewsWithIds.put(R.id.repeat_password_field, 10);
        sViewsWithIds.put(R.id.join_button, 11);
        sViewsWithIds.put(R.id.title_text, 12);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentJoinBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private FragmentJoinBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.EditText) bindings[6]
            , (com.google.android.material.textfield.TextInputLayout) bindings[5]
            , (android.widget.Button) bindings[11]
            , (android.widget.EditText) bindings[2]
            , (com.google.android.material.textfield.TextInputLayout) bindings[1]
            , (android.widget.EditText) bindings[8]
            , (com.google.android.material.textfield.TextInputLayout) bindings[7]
            , (android.widget.EditText) bindings[10]
            , (com.google.android.material.textfield.TextInputLayout) bindings[9]
            , (android.widget.EditText) bindings[4]
            , (com.google.android.material.textfield.TextInputLayout) bindings[3]
            , (android.widget.TextView) bindings[12]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}