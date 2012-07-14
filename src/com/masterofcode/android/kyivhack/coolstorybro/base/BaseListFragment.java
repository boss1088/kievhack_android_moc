package com.masterofcode.android.kyivhack.coolstorybro.base;

import java.util.ArrayList;

import android.content.Intent;
import android.widget.AdapterView;
import com.masterofcode.android.kyivhack.coolstorybro.CreateStoryActivity;
import com.masterofcode.android.kyivhack.coolstorybro.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BaseListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
	
	private ArrayList<String> elements = null;
	private ArrayList<Drawable> icons  = null;
	private View returnView            = null;
	
	public void setInstance(ArrayList<String> elements) {
		this.setElements(elements);
	}
	
	public void setInstance(ArrayList<String> elements, ArrayList<Drawable> icons) {
		this.setElements(elements);
		this.setIcons(icons);
	}
	
	public BaseListFragment() { }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setReturnView(inflater.inflate(R.layout.fragment_list, container, false));
		
		return getReturnView();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ListView list = (ListView) getReturnView().findViewById(R.id.fragment_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getElements());
		list.setAdapter(adapter);
        list.setOnItemClickListener(this);
	}

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(getActivity(), CreateStoryActivity.class));
    }

	public View getReturnView() {
		return returnView;
	}

	public void setReturnView(View returnView) {
		this.returnView = returnView;
	}

	public ArrayList<Drawable> getIcons() {
		return icons;
	}

	public void setIcons(ArrayList<Drawable> icons) {
		this.icons = icons;
	}

	public ArrayList<String> getElements() {
		return elements;
	}

	public void setElements(ArrayList<String> elements) {
		this.elements = elements;
	}

}
