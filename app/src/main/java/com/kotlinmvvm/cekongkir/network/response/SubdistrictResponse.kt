package com.kotlinmvvm.cekongkir.network.response

import com.google.gson.annotations.SerializedName

data class SubdistrictResponse(

		@field:SerializedName("rajaongkir")
		val rajaongkir: Rajaongkir? = null
) {
	data class Rajaongkir(

			@field:SerializedName("query")
			val query: Query? = null,

			@field:SerializedName("results")
			val results: List<ResultsItem?>? = null,

			@field:SerializedName("status")
			val status: Status? = null
	) {

		data class Status(

				@field:SerializedName("code")
				val code: Int? = null,

				@field:SerializedName("description")
				val description: String? = null
		)

		data class Query(

				@field:SerializedName("city")
				val city: String? = null
		)

		data class ResultsItem(

				@field:SerializedName("subdistrict_id")
				val subdistrictId: String? = null,

				@field:SerializedName("province")
				val province: String? = null,

				@field:SerializedName("province_id")
				val provinceId: String? = null,

				@field:SerializedName("city")
				val city: String? = null,

				@field:SerializedName("type")
				val type: String? = null,

				@field:SerializedName("subdistrict_name")
				val subdistrictName: String? = null,

				@field:SerializedName("city_id")
				val cityId: String? = null
		)

	}
}


