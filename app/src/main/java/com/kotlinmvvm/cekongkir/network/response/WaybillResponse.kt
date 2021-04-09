package com.kotlinmvvm.cekongkir.network.response

import com.google.gson.annotations.SerializedName

data class WaybillResponse(

		@field:SerializedName("rajaongkir")
		val rajaongkir: Rajaongkir? = null
) {
	data class Rajaongkir(

			@field:SerializedName("result")
			val result: Result? = null,

			@field:SerializedName("query")
			val query: Query? = null,

			@field:SerializedName("status")
			val status: Status? = null
	) {
		data class Query(

				@field:SerializedName("waybill")
				val waybill: String? = null,

				@field:SerializedName("courier")
				val courier: String? = null
		)

		data class Status(

				@field:SerializedName("code")
				val code: Int? = null,

				@field:SerializedName("description")
				val description: String? = null
		)

		data class Result(

				@field:SerializedName("summary")
				val summary: Summary? = null,

				@field:SerializedName("manifest")
				val manifest: List<ManifestItem?>? = null,

				@field:SerializedName("delivered")
				val delivered: Boolean? = null,

				@field:SerializedName("details")
				val details: Details? = null,

				@field:SerializedName("delivery_status")
				val deliveryStatus: DeliveryStatus? = null
		) {
			data class Summary(

					@field:SerializedName("waybill_date")
					val waybillDate: String? = null,

					@field:SerializedName("courier_name")
					val courierName: String? = null,

					@field:SerializedName("waybill_number")
					val waybillNumber: String? = null,

					@field:SerializedName("courier_code")
					val courierCode: String? = null,

					@field:SerializedName("origin")
					val origin: String? = null,

					@field:SerializedName("destination")
					val destination: String? = null,

					@field:SerializedName("service_code")
					val serviceCode: String? = null,

					@field:SerializedName("receiver_name")
					val receiverName: String? = null,

					@field:SerializedName("shipper_name")
					val shipperName: String? = null,

					@field:SerializedName("status")
					val status: String? = null
			)


			data class DeliveryStatus(

					@field:SerializedName("pod_receiver")
					val podReceiver: String? = null,

					@field:SerializedName("pod_time")
					val podTime: String? = null,

					@field:SerializedName("pod_date")
					val podDate: String? = null,

					@field:SerializedName("status")
					val status: String? = null
			)

			data class Details(

					@field:SerializedName("shipper_address2")
					val shipperAddress2: String? = null,

					@field:SerializedName("waybill_date")
					val waybillDate: String? = null,

					@field:SerializedName("shipper_address3")
					val shipperAddress3: String? = null,

					@field:SerializedName("receiver_city")
					val receiverCity: String? = null,

					@field:SerializedName("origin")
					val origin: String? = null,

					@field:SerializedName("shipper_address1")
					val shipperAddress1: String? = null,

					@field:SerializedName("destination")
					val destination: String? = null,

					@field:SerializedName("weight")
					val weight: String? = null,

					@field:SerializedName("waybill_time")
					val waybillTime: String? = null,

					@field:SerializedName("receiver_address3")
					val receiverAddress3: String? = null,

					@field:SerializedName("waybill_number")
					val waybillNumber: String? = null,

					@field:SerializedName("receiver_address2")
					val receiverAddress2: String? = null,

					@field:SerializedName("receiver_address1")
					val receiverAddress1: String? = null,

					@field:SerializedName("shippper_name")
					val shippperName: String? = null,

					@field:SerializedName("shipper_city")
					val shipperCity: String? = null,

					@field:SerializedName("receiver_name")
					val receiverName: String? = null
			)

			data class ManifestItem(

					@field:SerializedName("city_name")
					val cityName: String? = null,

					@field:SerializedName("manifest_time")
					val manifestTime: String? = null,

					@field:SerializedName("manifest_description")
					val manifestDescription: String? = null,

					@field:SerializedName("manifest_date")
					val manifestDate: String? = null
			)
		}


	}

}


