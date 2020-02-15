package com.example.schoolproject.Activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.schoolproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Expandalelistadapter extends BaseExpandableListAdapter
{
    private List<String> Class_titles;
    private HashMap<String,List<String>> Course_titles;
    private Context ctx;
    Expandalelistadapter()
    {

    }
    Expandalelistadapter(Context ctx,List<String> Class_titles,HashMap<String,List<String>> Course_titles)
    {
        this.Class_titles=Class_titles;
        this.ctx=ctx;
        this.Course_titles=Course_titles;

    }
    @Override
    public int getGroupCount() {
        if(Class_titles.size()>0)
        Log.i("getGroupCount: ",Course_titles+"");
        return Class_titles.size();
    }

    @Override
    public int getChildrenCount(int i) {


        return Course_titles.get(Class_titles.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return Class_titles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return Course_titles.get(Class_titles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String classtitle=  (String)this.getGroup(i);
        if(view==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.class_list_titles,null,false);

        }
        TextView textView=(TextView)view.findViewById(R.id.class_titles);
        textView.setText(classtitle);

        Log.i("getGroupView: ",view.toString());

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String coursetitle=(String) this.getChild(i,i1);
        if(view==null)
        {

            LayoutInflater layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.corse_list_titles,null,false);

        }
        TextView textView=(TextView)view.findViewById(R.id.course_titles);
        textView.setText(coursetitle);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
