package biz.gelicon.artfarm.app.view.drugstoresregion;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import biz.gelicon.artfarm.app.R;

/**
 * Фрагмент, хранящий карту, отображающую аптеки в регионе
 */
@EFragment(R.layout.drugstores_map_fragment)
public class ViewDrugstoresMapFragment extends Fragment {

    private final String MAPKIT_API_KEY = "402a3c81-654e-48d8-931a-dae54ea860ac";

    @ViewById(R.id.address_map_view)
    MapView mapView;


    @AfterInject
    void afterInject() {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        if (getContext() != null) {
            MapKitFactory.initialize(getContext());
        }
    }

    MapObjectCollection mapObjects;

    @AfterViews
    void afterViews() {
        mapObjects = mapView.getMap().getMapObjects().addCollection();
        mapObjects.clear();
        ImageProvider icon = ImageProvider.fromResource(getContext(), R.drawable.not_available);
        createMarker(icon, new Point(56.235689, 33.567896));
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    private void createMarker(ImageProvider imageProvider, Point point) {
        Point location = new Point(point.getLatitude(), point.getLongitude());
        PlacemarkMapObject mark = mapObjects.addPlacemark(location);
        mark.setOpacity(0.5f);
        mark.setIcon(imageProvider);
        mark.setDraggable(true);
        mark.addTapListener(new MapObjectTapListener() {
            @Override
            public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
                return true;
            }
        });
        move(point);
    }

    /**
     * Передвижение карты
     *
     * @param location
     */
    private void move(Point location) {
        mapView.getMap().move(
                new CameraPosition(location, 15.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 3),
                null);
    }
}
