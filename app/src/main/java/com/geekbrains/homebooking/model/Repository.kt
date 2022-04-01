import com.geekbrains.homebooking.model.City
import com.geekbrains.homebooking.model.Hotel


interface Repository {
    fun getCityFromServer(): City
    fun getCityFromLocalStorage(): List<City>
    fun getHotelFromLocalStorage(city: City): List<Hotel>
}