
package com.mycompany.sdm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SenData {

    @SerializedName("date")
    @Expose
    public String date;
    
    @SerializedName("temperature")
    @Expose
    public Integer temp;
    
    @SerializedName("humidity")
    @Expose
    public Integer hum;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("date:", date).append(" temperature:", temp).append(" humidity:", hum).toString();
    }

}
