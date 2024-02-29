package edu.famu.alertallergy.models.UserSettings;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AUserSettings {
    @DocumentId
    protected @Nullable String settingId;
    protected String language;
    protected Map<String,Object> notificationPreferences;
    protected Map<String,Object> displayPreferences;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public void setCreatedAt(String createdAt) throws ParseException, ParseException {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

    public void setUpdatedAt(String updatedAt) throws ParseException {
        this.updatedAt = Timestamp.fromProto(Timestamps.parse(updatedAt));
    }

    /*
    public void updateUpdateDateTime(Timestamp updatedAt) {
        this.updatedAt = updatedAt;

    }

     */
}
