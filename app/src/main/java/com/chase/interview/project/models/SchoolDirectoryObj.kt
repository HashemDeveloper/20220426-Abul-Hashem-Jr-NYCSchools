package com.chase.interview.project.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class SchoolDirectoryObj(
    @SerializedName("dbn")
    var dbn: String? = null,

    @SerializedName("school_name")
    var schoolName: String="",

    @SerializedName("borocode")
    var borocode: String? = null,

    @SerializedName("overview_paragraph")
    var overviewParagraph: String? = null,

    @SerializedName("diplomaendorsements")
    var diplomaendorsements: String = "",

    @SerializedName("academicopportunities1")
    var academicopportunities1: String? = null,

    @SerializedName("academicopportunities2")
    var academicopportunities2: String? = null,

    @SerializedName("academicopportunities3")
    var academicopportunities3: String? = null,

    @SerializedName("academicopportunities4")
    var academicopportunities4: String? = null,

    @SerializedName("academicopportunities5")
    var academicopportunities5: String? = null,

    @SerializedName("ell_programs")
    var ellPrograms: String = "",

    @SerializedName("language_classes")
    var languageClasses: String? = null,

    @SerializedName("advancedplacement_courses")
    var advancedplacementCourses: String? = null,

    @SerializedName("neighborhood")
    var neighborhood: String? = null,

    @SerializedName("primary_address_line_1")
    var primaryAddressLine1: String? = null,

    @SerializedName("city")
    var city: String? = null,

    @SerializedName("state_code")
    var stateCode: String? = null,

    @SerializedName("postcode")
    var postcode: String? = null,

    @SerializedName("shared_space")
    var sharedSpace: String? = null,

    @SerializedName("campus_name")
    var campusName: String? = null,

    @SerializedName("building_code")
    var buildingCode: String? = null,

    @SerializedName("location")
    var location: String? = null,

    @SerializedName("phone_number")
    var phoneNumber: String? = null,

    @SerializedName("fax_number")
    var faxNumber: String? = null,

    @SerializedName("school_email")
    var schoolEmail: String? = null,

    @SerializedName("website")
    var website: String? = null,

    @SerializedName("subway")
    var subway: String? = null,

    @SerializedName("bus")
    var bus: String? = null,

    @SerializedName("grades2019")
    var grades2019: String? = null,

    @SerializedName("finalgrades")
    var finalgrades: String? = null,

    @SerializedName("total_students")
    var totalStudents: String? = null,

    @SerializedName("start_time")
    var startTime: String? = null,

    @SerializedName("end_time")
    var endTime: String? = null,

    @SerializedName("psal_sports_boys")
    var psalSportsBoys: String? = null,

    @SerializedName("psal_sports_girls")
    var psalSportsGirls: String? = null,

    @SerializedName("graduation_rate")
    var graduationRate: String? = null,

    @SerializedName("attendance_rate")
    var attendanceRate: String? = null,

    @SerializedName("pct_stu_enough_variety")
    var pctStuEnoughVariety: String? = null,

    @SerializedName("college_career_rate")
    var collegeCareerRate: String? = null,

    @SerializedName("pct_stu_safe")
    @Expose(serialize = false)
    var pctStuSafe: String? = null,

    @SerializedName("school_accessibility")
    var schoolAccessibility: String? = null,

    @SerializedName("prgdesc1")
    var prgdesc1: String? = null,

    @SerializedName("offer_rate1_1")
    var offerRate11: String? = null,

    @SerializedName("program1")
    var program1: String? = null,

    @SerializedName("code1")
    @Expose(serialize = false)
    var code1: String? = null,

    @SerializedName("interest1")
    @Expose
    var interest1: String? = null,

    @SerializedName("method1")
    @Expose(serialize = false)
    var method1: String? = null,

    @SerializedName("seats9ge1")
    @Expose(serialize = false)
    var seats9ge1: String? = null,

    @SerializedName("grade9gefilledflag1")
    @Expose(serialize = false)
    var grade9gefilledflag1: String? = null,

    @SerializedName("grade9geapplicants1")
    @Expose(serialize = false)
    var grade9geapplicants1: String? = null,

    @SerializedName("grade9geapplicantsperseat1")
    @Expose(serialize = false)
    var grade9geapplicantsperseat1: String? = null,

    @SerializedName("seats9swd1")
    @Expose(serialize = false)
    var seats9swd1: String? = null,

    @SerializedName("grade9swdfilledflag1")
    @Expose(serialize = false)
    var grade9swdfilledflag1: String? = null,

    @SerializedName("grade9swdapplicants1")
    @Expose(serialize = false)
    var grade9swdapplicants1: String? = null,

    @SerializedName("grade9swdapplicantsperseat1")
    @Expose(serialize = false)
    var grade9swdapplicantsperseat1: String? = null,

    @SerializedName("seats101")
    @Expose(serialize = false)
    var seats101: String? = null,

    @SerializedName("admissionspriority11")
    @Expose(serialize = false)
    var admissionspriority11: String? = null,

    @SerializedName("admissionspriority21")
    @Expose(serialize = false)
    var admissionspriority21: String? = null,

    @SerializedName("borough")
    @Expose
    var borough: String? = null,
    @SerializedName("latitude")
    var latitude: String? = null,
    @SerializedName("longitude")
    var longitude: String? = null,

    @SerializedName("community_board")
    @Expose(serialize = false)
    var communityBoard: String? = null,

    @SerializedName("council_district")
    @Expose(serialize = false)
    var councilDistrict: String? = null,

    @SerializedName("census_tract")
    @Expose(serialize = false)
    var censusTract: String? = null,

    @SerializedName("bin")
    @Expose(serialize = false)
    var bin: String? = null,

    @SerializedName("bbl")
    @Expose(serialize = false)
    var bbl: String? = null,

    @SerializedName("nta")
    @Expose(serialize = false)
    var nta: String? = null,

    @SerializedName("geocoded_column")
    @Expose
    var geocodedColumn: @RawValue GeocodedColumn? = null,

    @SerializedName(":@computed_region_efsh_h5xi")
    @Expose(serialize = false)
    var computedRegionEfshH5xi: String? = null,

    @SerializedName(":@computed_region_f5dn_yrer")
    @Expose(serialize = false)
    var computedRegionF5dnYrer: String? = null,

    @SerializedName(":@computed_region_yeji_bk3q")
    @Expose(serialize = false)
    var computedRegionYejiBk3q: String? = null,

    @SerializedName(":@computed_region_92fq_4b7q")
    @Expose(serialize = false)
    var computedRegion92fq4b7q: String? = null,

    @SerializedName(":@computed_region_sbqj_enih")
    @Expose(serialize = false)
    var computedRegionSbqjEnih: String? = null
):Parcelable

