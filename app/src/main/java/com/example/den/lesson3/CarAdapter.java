package com.example.den.lesson3;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarAdapter extends ArrayAdapter {

    private ArrayList<Car> cars;
    private Activity activity;

    public CarAdapter(Activity activity, int resource, ArrayList<Car> cars) {
        super(activity, resource, cars);
        this.cars = cars;
        this.activity = activity;
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {

        Car currentCar = cars.get(position);
        if (convertView == null) {
            convertView = activity.getLayoutInflater()
                    .inflate(R.layout.custom_item, null, false);
        }
        if (convertView.getTag() == null) {
            saveCarToTag(convertView);
        }
        loadCarFromTag(convertView, currentCar);
        return convertView;
    }

    private void saveCarToTag(View convertView) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.carName = convertView.findViewById(R.id.car_name);
        viewHolder.carImage = convertView.findViewById(R.id.car_image);
        convertView.setTag(viewHolder);
    }

    private void loadCarFromTag(View convertView, Car currentCar) {
        TextView carName = ((ViewHolder) convertView.getTag()).carName;
        ImageView carImage = ((ViewHolder) convertView.getTag()).carImage;
        carName.setText(currentCar.name);
        carImage.setImageDrawable(currentCar.img);
    }

}
