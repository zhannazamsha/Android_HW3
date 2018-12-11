package com.example.den.lesson3;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Car> cars = collectCars();
        CarAdapter carAdapter = new CarAdapter(this, 0, cars);
        GridView carGrid = drawCarGridView(carAdapter, cars);
        setContentView(carGrid);
    }

    private GridView drawCarGridView(CarAdapter carAdapter, final ArrayList<Car> cars) {
        GridView carGrid = new GridView(this);
        carGrid.setNumColumns(2);
        carGrid.setColumnWidth(60);
        carGrid.setVerticalSpacing(20);
        carGrid.setHorizontalSpacing(20);
        carGrid.setAdapter(carAdapter);
        carGrid.setOnItemClickListener(selectItemListener(cars));
        return carGrid;
    }

    private ArrayList<Car> collectCars() {
        Resources res = getResources();
        String[] carsNameArray = res.getStringArray(R.array.car_types);
        ArrayList<Car> cars = new ArrayList<>();
        for (String name : carsNameArray) {
            int resourceId = res.getIdentifier(name.toLowerCase(), "drawable", getPackageName());
            Drawable image = res.getDrawable(resourceId);
            Car car = new Car(name, image);
            cars.add(car);
        }
        return cars;
    }

    private AdapterView.OnItemClickListener selectItemListener(final ArrayList<Car> cars){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long rowId) {
                String message = "You clicked on " + cars.get(position).name;
                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
        };
    }

}
