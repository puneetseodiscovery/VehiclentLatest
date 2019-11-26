package com.vehiclent.responseModelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubmitQueryResponseModel {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("service_id")
        @Expose
        private String serviceId;
        @SerializedName("Message")
        @Expose
        private String message;
        @SerializedName("click_action")
        @Expose
        private String clickAction;
        @SerializedName("Latitude")
        @Expose
        private String latitude;
        @SerializedName("Longitude")
        @Expose
        private String longitude;
        @SerializedName("Partner_Id")
        @Expose
        private String partnerId;
        @SerializedName("dataArr")
        @Expose
        private List<DataArr> dataArr = null;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getClickAction() {
            return clickAction;
        }

        public void setClickAction(String clickAction) {
            this.clickAction = clickAction;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public List<DataArr> getDataArr() {
            return dataArr;
        }

        public void setDataArr(List<DataArr> dataArr) {
            this.dataArr = dataArr;
        }
    }

    public class DataArr {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
}
