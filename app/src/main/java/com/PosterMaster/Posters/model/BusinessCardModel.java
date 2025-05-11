package com.PosterMaster.Posters.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.PosterMaster.Posters.BR;

public class BusinessCardModel extends BaseObservable {
    private String id;
    private String title;
    private String thumb_url;
    private String blade_name;
    private String premium;
    private String status;
    private String updated_at;
    private String created_at;

    public BusinessCardModel() {
        // Default constructor required for data binding
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
        notifyPropertyChanged(BR.thumb_url);
    }

    public String getBlade_name() {
        return blade_name;
    }

    public void setBlade_name(String blade_name) {
        this.blade_name = blade_name;
        notifyPropertyChanged(BR.blade_name);
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
        notifyPropertyChanged(BR.premium);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyPropertyChanged(BR.status);
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
        notifyPropertyChanged(BR.updated_at);
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
        notifyPropertyChanged(BR.created_at);
    }
}
