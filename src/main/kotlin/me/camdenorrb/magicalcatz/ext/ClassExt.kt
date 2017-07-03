package me.camdenorrb.magicalcatz.ext


@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any, R> getPrivateField(fieldName: String, obj: T? = null): R? {
	val field = T::class.java.getDeclaredField(fieldName)
	field.isAccessible = true
	return field.get(obj) as? R
}