package com.troop.freedcam.ui.menu;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.troop.freedcam.R;
import com.troop.freedcam.i_camera.parameters.AbstractManualParameter;
import com.troop.freedcam.sonyapi.parameters.manual.BaseManualParameterSony;

/**
 * Created by troop on 01.09.2014.
 */
public class ManualMenuItem extends LinearLayout implements View.OnClickListener, AbstractManualParameter.I_ManualParameterEvent
{
    Context context;
    public TextView textViewName;
    public TextView textViewValue;
    private LinearLayout toggleButton;
    public final String name;
    ManualMenuHandler manualMenuHandler;
    public AbstractManualParameter manualParameter;
    boolean isChecked = false;
    boolean isVisibile = false;
    boolean isSetSupported = false;
    int btncolor;
    int txtcolor;

    public ManualMenuItem(Context context, String name, ManualMenuHandler manualMenuHandler) {
        super(context);
        this.context =context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.manual_menu_item, this);
        this.textViewName = (TextView)findViewById(R.id.manual_item_Text);
        this.textViewValue = (TextView)findViewById(R.id.manual_item_valueText);
        this.name = name;
        this.manualMenuHandler = manualMenuHandler;
        txtcolor = textViewName.getCurrentTextColor();

        textViewName.setText(name);
        //set int to textviews always as string or you will get and res not found ex!!

        toggleButton = (LinearLayout)findViewById(R.id.manual_item);
        Drawable background = toggleButton.getBackground();
        if (background instanceof ColorDrawable)
            btncolor = ((ColorDrawable) background).getColor();

    }

    public void SetAbstractManualParameter(AbstractManualParameter parameter)
    {
        this.manualParameter = parameter;
        manualParameter.addEventListner(this);
        if (manualParameter.IsSupported())
        {
            textViewValue.setText(parameter.GetValue() + "");
            toggleButton.setOnClickListener(this);
            isSetSupported = true;
            onIsSupportedChanged(true);
        }
    }

    @Override
    public void onClick(View v)
    {
        if (isSetSupported)
            manualMenuHandler.DisableOtherItems(name);
    }

    public void DisableItem()
    {
        isChecked = false;
    }

    public void EnableItem() {isChecked = true;}



    //**
    // AbstractManualParameter.I_ManualParameterEvent
    // AbstractManualParameter.I_ManualParameterEvent
    @Override
    public void onIsSupportedChanged(boolean supported)
    {
        if (supported && !isVisibile)
        {
            manualMenuHandler.manualMenu.addView(this);
            isVisibile =true;
        }
        if (!supported && isVisibile) {
            manualMenuHandler.manualMenu.removeView(this);
            isVisibile =false;
        }

    }

    @Override
    public void onIsSetSupportedChanged(boolean supported)
    {
        if (supported)
        {
            toggleButton.setBackgroundColor(btncolor);
            textViewName.setTextColor(txtcolor);
            textViewValue.setTextColor(txtcolor);
            toggleButton.setClickable(true);
            toggleButton.setOnClickListener(this);
            isSetSupported = true;
        }
        else
        {
            toggleButton.setBackgroundColor(txtcolor);
            textViewName.setTextColor(btncolor);
            textViewValue.setTextColor(btncolor);
            toggleButton.setOnClickListener(null);
            toggleButton.setClickable(false);
            isSetSupported = false;
        }

    }

    @Override
    public void onMaxValueChanged(int max) {

    }

    @Override
    public void onMinValueChanged(int min) {

    }

    @Override
    public void onCurrentValueChanged(int current)
    {
        if (manualParameter instanceof BaseManualParameterSony)
            textViewValue.setText(((BaseManualParameterSony) manualParameter).GetStringValue());
    }

    //
    // AbstractManualParameter.I_ManualParameterEvent
    // AbstractManualParameter.I_ManualParameterEvent
    //**
}
