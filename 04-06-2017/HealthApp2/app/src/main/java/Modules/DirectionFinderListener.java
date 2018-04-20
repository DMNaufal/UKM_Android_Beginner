package Modules;

import java.util.List;

/**
 * Created by Ally on 17/06/2017.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}