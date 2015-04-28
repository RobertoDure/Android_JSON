package br.com.alertaonline;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;
import br.com.alerta_online.entidades_usuarios.Principal_Usuarios_Activity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity {
    private SupportMapFragment mapFrag ;
    private GoogleMap map;
    LatLng latlngTchouch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GoogleMapOptions options = new GoogleMapOptions();
		options.zOrderOnTop(true);
		 mapFrag = SupportMapFragment.newInstance(options);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.llContainer, mapFrag);
		ft.commit();
		
		
	}
	@Override
	public void onResume(){
		super.onResume();
		
		new Thread(){
			public void run(){
				while (mapFrag.getMap() == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						configMap();
					}
				});
			}
			
		}.start();
	}
	public void configMap(){
		map = mapFrag.getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		LatLng latlng = new LatLng(-7.22448236,-35.87081194);
		CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(13).bearing(0).tilt(0).build();
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
		map.animateCamera(update,3000, new CancelableCallback() {
			
			@Override
			public void onFinish() {	
			map.setOnMapLongClickListener(new OnMapLongClickListener() {
				
				@Override
				public void onMapLongClick(LatLng point) {
					    Projection proj = map.getProjection();
				        Point coord = proj.toScreenLocation(point);
					 Toast.makeText( MainActivity.this, "Click longo\n" + "Lat: " + point.latitude + "\n" +"Lng: " + point.longitude + "\n" + "X: " + coord.x + " - Y: " + coord.y, Toast.LENGTH_SHORT).show();
					 LatLng NEWARK = new LatLng( point.latitude, point.longitude);
                       String lat = String.valueOf(point.latitude);
                       String lng = String.valueOf(point.longitude);
						GroundOverlayOptions newarkMap = new GroundOverlayOptions()
						        .image(BitmapDescriptorFactory.fromResource(R.drawable.sirene_overlay))
						        .position(NEWARK, 50f, 50f);
						// Add an overlay to the map, retaining a handle to the GroundOverlay object.
						GroundOverlay imageOverlay = map.addGroundOverlay(newarkMap);
						
						Intent it = new Intent(MainActivity.this,Principal_Usuarios_Activity.class);
						it.putExtra("latitude", lat);
						it.putExtra("longitude", lng);
						startActivity(it);
				}
			});
			
			
			}
			
			@Override
			public void onCancel() {
				Log.d("Mapa", "onCancel().CancelableCallback()");		
			}
		});
	}

}
