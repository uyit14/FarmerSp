package com.example.uytai.farmersp.thuonglai.nongsan;

import com.google.android.gms.maps.model.Marker;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 09/07/2018
 */
public interface MapEventListener {
        public void onMapReady();

        void onMarkerClicked(Marker marker);
}
