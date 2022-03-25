import com.geekbrains.homebooking.model.City

interface Repository {
    fun getCityFromServer(): City
    fun getCityFromLocalStorage(): List<City>
}