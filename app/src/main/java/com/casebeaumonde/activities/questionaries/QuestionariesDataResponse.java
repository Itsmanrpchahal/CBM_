package com.casebeaumonde.activities.questionaries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionariesDataResponse {

@SerializedName("code")
@Expose
private String code;
@SerializedName("data")
@Expose
private Data data;

public String getCode() {
return code;
}

public void setCode(String code) {
this.code = code;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

    public class Data {

        @SerializedName("customer")
        @Expose
        private Customer customer;
        @SerializedName("business")
        @Expose
        private Business business;
        @SerializedName("influencer")
        @Expose
        private Influencer influencer;

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public Business getBusiness() {
            return business;
        }

        public void setBusiness(Business business) {
            this.business = business;
        }

        public Influencer getInfluencer() {
            return influencer;
        }

        public void setInfluencer(Influencer influencer) {
            this.influencer = influencer;
        }

        public class Customer {

            @SerializedName("astrological_sign")
            @Expose
            private List<String> astrologicalSign = null;
            @SerializedName("body_type")
            @Expose
            private List<String> bodyType = null;
            @SerializedName("eye_color")
            @Expose
            private List<String> eyeColor = null;
            @SerializedName("hair_color")
            @Expose
            private List<String> hairColor = null;
            @SerializedName("characterstic")
            @Expose
            private List<String> characterstic = null;
            @SerializedName("stores")
            @Expose
            private List<String> stores = null;
            @SerializedName("drives_to_shop")
            @Expose
            private List<String> drivesToShop = null;
            @SerializedName("fashion_events")
            @Expose
            private List<String> fashionEvents = null;
            @SerializedName("like_cbm")
            @Expose
            private List<String> likeCbm = null;
            @SerializedName("brand")
            @Expose
            private List<Brand> brand = null;
            @SerializedName("country")
            @Expose
            private List<Country> country = null;

            public List<String> getAstrologicalSign() {
                return astrologicalSign;
            }

            public void setAstrologicalSign(List<String> astrologicalSign) {
                this.astrologicalSign = astrologicalSign;
            }

            public List<String> getBodyType() {
                return bodyType;
            }

            public void setBodyType(List<String> bodyType) {
                this.bodyType = bodyType;
            }

            public List<String> getEyeColor() {
                return eyeColor;
            }

            public void setEyeColor(List<String> eyeColor) {
                this.eyeColor = eyeColor;
            }

            public List<String> getHairColor() {
                return hairColor;
            }

            public void setHairColor(List<String> hairColor) {
                this.hairColor = hairColor;
            }

            public List<String> getCharacterstic() {
                return characterstic;
            }

            public void setCharacterstic(List<String> characterstic) {
                this.characterstic = characterstic;
            }

            public List<String> getStores() {
                return stores;
            }

            public void setStores(List<String> stores) {
                this.stores = stores;
            }

            public List<String> getDrivesToShop() {
                return drivesToShop;
            }

            public void setDrivesToShop(List<String> drivesToShop) {
                this.drivesToShop = drivesToShop;
            }

            public List<String> getFashionEvents() {
                return fashionEvents;
            }

            public void setFashionEvents(List<String> fashionEvents) {
                this.fashionEvents = fashionEvents;
            }

            public List<String> getLikeCbm() {
                return likeCbm;
            }

            public void setLikeCbm(List<String> likeCbm) {
                this.likeCbm = likeCbm;
            }

            public List<Brand> getBrand() {
                return brand;
            }

            public void setBrand(List<Brand> brand) {
                this.brand = brand;
            }

            public List<Country> getCountry() {
                return country;
            }

            public void setCountry(List<Country> country) {
                this.country = country;
            }


            public class Brand {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("name")
                @Expose
                private String name;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

            }

            public class Country {

                @SerializedName("id")
                @Expose
                private Integer id;
                @SerializedName("code")
                @Expose
                private String code;
                @SerializedName("name")
                @Expose
                private String name;
                @SerializedName("state")
                @Expose
                private List<State> state = null;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<State> getState() {
                    return state;
                }

                public void setState(List<State> state) {
                    this.state = state;
                }

                public class State {

                    @SerializedName("id")
                    @Expose
                    private Integer id;
                    @SerializedName("country_id")
                    @Expose
                    private Integer countryId;
                    @SerializedName("code")
                    @Expose
                    private String code;
                    @SerializedName("name")
                    @Expose
                    private String name;

                    public Integer getId() {
                        return id;
                    }

                    public void setId(Integer id) {
                        this.id = id;
                    }

                    public Integer getCountryId() {
                        return countryId;
                    }

                    public void setCountryId(Integer countryId) {
                        this.countryId = countryId;
                    }

                    public String getCode() {
                        return code;
                    }

                    public void setCode(String code) {
                        this.code = code;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                }

            }

        }

        public class Business {

            @SerializedName("struggle_with")
            @Expose
            private List<String> struggleWith = null;
            @SerializedName("partnered_with")
            @Expose
            private List<String> partneredWith = null;

            public List<String> getStruggleWith() {
                return struggleWith;
            }

            public void setStruggleWith(List<String> struggleWith) {
                this.struggleWith = struggleWith;
            }

            public List<String> getPartneredWith() {
                return partneredWith;
            }

            public void setPartneredWith(List<String> partneredWith) {
                this.partneredWith = partneredWith;
            }

        }

        public class Influencer {

            @SerializedName("time_spend")
            @Expose
            private List<String> timeSpend = null;
            @SerializedName("help_users")
            @Expose
            private List<String> helpUsers = null;

            public List<String> getTimeSpend() {
                return timeSpend;
            }

            public void setTimeSpend(List<String> timeSpend) {
                this.timeSpend = timeSpend;
            }

            public List<String> getHelpUsers() {
                return helpUsers;
            }

            public void setHelpUsers(List<String> helpUsers) {
                this.helpUsers = helpUsers;
            }

        }
    }
}