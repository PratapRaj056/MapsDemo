package prs.darkeagle.mapsdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static prs.darkeagle.mapsdemo.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
	
	private GoogleMap mMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(map);
		mapFragment.getMapAsync(this);
	}
	
	
	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
		methodRequiresTwoPermission();
		
	}
	
	@AfterPermissionGranted(33)
	private void methodRequiresTwoPermission() {
		String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
		if (EasyPermissions.hasPermissions(this, perms)) {
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return;
			}
			
			mMap.setMyLocationEnabled(true);
			// Add a marker in Sydney and move the camera
			LatLng sydney = new LatLng(12.836214, 80.087016);
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.position(sydney).title("Marker in Guduvancherry").snippet("Villa-Boyz");
			mMap.addMarker(markerOptions);
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
			
			/*mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
			// Flat markers will rotate when the map is rotated,
			// and change perspective when the map is tilted.
			mMap.addMarker(new MarkerOptions()
					.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_input_add))
					.position(sydney)
					.flat(true)
					.rotation(360));
			CameraPosition cameraPosition = CameraPosition.builder()
					.target(sydney)
					.zoom(13)
					.bearing(90)
					.build();
			// Animate the change in camera view over 2 seconds
			mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
					2000, null);*/
		} else {
			// Do not have permissions, request them now
			EasyPermissions.requestPermissions(this, "PratapRaj",
					33, perms);
		}
	}
	
	
}
