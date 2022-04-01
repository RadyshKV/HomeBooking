interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}