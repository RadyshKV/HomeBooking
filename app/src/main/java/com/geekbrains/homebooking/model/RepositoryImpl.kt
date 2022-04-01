import com.geekbrains.homebooking.model.*


class RepositoryImpl : Repository {
    override fun getCityFromServer() = City("Москва", 50, 0)
    override fun getCityFromLocalStorage() = getCities()
    override fun getHotelFromLocalStorage(city: City) = getHotels(city)
}