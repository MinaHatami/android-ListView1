package com.minahatami.listview1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<Pic> myPics = new ArrayList<Pic>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*myPics.add(new Pic(R.drawable.ic_launcher, "documents/pictures", "img0"));
		myPics.add(new Pic(R.drawable.ic_launcher, "documents/pictures", "img1"));
		myPics.add(new Pic(R.drawable.ic_launcher, "documents/pictures", "img2"));
		myPics.add(new Pic(R.drawable.ic_launcher, "documents/pictures", "img3"));*/

		List<String> selfies = getSelfies();

		for (String file : selfies) {
			myPics.add(new Pic(0, file, file));
		}
		
		populateListView();
	}

	private void populateListView() {
		ArrayAdapter<Pic> adapter = new MyArrayAdapter();
		ListView myPicsList = (ListView) findViewById(R.id.myPicsList);
		myPicsList.setAdapter(adapter);

	}

	private class MyArrayAdapter extends ArrayAdapter<Pic> {

		public MyArrayAdapter() {
			super(MainActivity.this, R.layout.activity_item_list, myPics);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View myView = convertView;

			if (myView == null) {
				myView = getLayoutInflater().inflate(
						R.layout.activity_item_list, parent, false);
			}
			Pic currentPic = myPics.get(position);

			ImageView image = (ImageView) myView.findViewById(R.id.image);
			TextView path = (TextView) myView.findViewById(R.id.path);
			TextView name = (TextView) myView.findViewById(R.id.name);

			Bitmap bm = getBitmap(currentPic.getPath());
			image.setImageBitmap(bm);
			path.setText(currentPic.getPath());
			name.setText(currentPic.getName());

			return myView;
		}

	}
	
	private Bitmap getBitmap(String path)
	{
		return BitmapFactory.decodeFile(path);
	}

	public File getRootDirectoy() {
		return Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	}

	// Get the selfie photos from a folder called "My Selfies" and save them in a list called "selfieItems".
	public List<String> getSelfies() {
		final String pathName = "My Selfies";
		List<String> selfieItems = new ArrayList<String>();
		final File selfieFolder = new File(getRootDirectoy(), pathName);

		if (!selfieFolder.exists()) {
			selfieFolder.mkdir();
		}
		else {
			File[] files = selfieFolder.listFiles();
			for (File file : files) {
				if (!file.isDirectory()
						&& file.getAbsolutePath().endsWith(".jpg")) {
					selfieItems.add(file.getAbsolutePath());
				}
			}
		}

		return selfieItems;
	}
}
