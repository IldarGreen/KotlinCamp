package org.example.data

data class Incident(
    val x: Int,
    val y: Int,
    val description: String,
    val applicantPhoneNumber: String?,
    var type: IncidentType?
)

enum class IncidentType(val type: String) {
    FIRE("Fire"),
    GAS("Gas leak"),
    CAT("Cat on the tree"),
    SKELETON("Skeleton under the tree"),
    PRINCES("Princess in trouble")
}
