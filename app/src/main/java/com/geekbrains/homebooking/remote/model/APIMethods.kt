package com.geekbrains.homebooking.remote.model

enum class APIMethods(val value: String) {
    GEO_COUNTRY("Geo.Country"),
    GEO_REGION("Geo.Region"),
    GEO_RESORT("Geo.Resort"),
    GEO_CITY("Geo.City"),
    GEO_NAME("Geo.Name"),
    HOTEL_DESC("Hotel.Desc"),
    HOTEL_MEALS("Hotel.Meals"),
    HOTEL_OFFERS("Hotel.Offers"),
    HOTEL_OFFER("Hotel.Offer"),
    HOTEL_QUOTAS("Hotel.Quotas"),
    HOTEL_COUNT("Hotel.Count"),
    EXCURSION_EXCURSIONS("Excursion.Excursions"),
    EXCURSION_EXCURSION_OFFERS("Excursion.ExcursionOffers"),
    BOOKING_SET("Booking.Set"),
    BOOKING_STATUS("Booking.Status"),
    BOOKING_CANCEL("Booking.Cancel")
}