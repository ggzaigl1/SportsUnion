package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by Stefan on 2018/1/29.
 */

public class GroupMultiToAppBean {
    /**
     * physicalDiagnosisEntity : {"id":3,"physicalName":"健康状态良好，体重过重，心肺功能差","physicalCode":"WEIGHT_BAD","typeId":"3","physicalContent":"健康状态良好，体重过重，心肺功能差"}
     * recipesMotionEntities : [{"id":3,"recipesName":"火腿肌肉饭","type":"1","mealType":"1","recipesType":"2","ageMix":"11","ageMax":"13","foodValue":"高蛋白，氨基酸","recipesContent":"富含人体吸收元素","image":null}]
     * diagnosticPrescriptionEntity : {"id":3,"diagnosticId":3,"diagnosticPurpose":"增重和锻炼","sportsIds":"1,2,3","recipesIds":"1,2,3,4","diagnosticContent":"运动项目：乒乓球，篮球 运动12周，每次运动40到60分钟"}
     * sportsMotionEntities : [{"id":1,"sportsName":"跳绳","sportsContent":"双手持绳2端连续跳","sportsTime":"30","sportsDifficulty":"2"},{"id":2,"sportsName":"篮球","sportsContent":"双手持球运动","sportsTime":"40","sportsDifficulty":"3"},{"id":3,"sportsName":"乒乓球","sportsContent":"持球拍双人运动","sportsTime":"30","sportsDifficulty":"3"}]
     * supplementaryKnowledgeEntities : [{"id":3,"supplementaryName":"形体训练3","supplementaryContent":"形体训练队学生的健康有好处","image":null,"status":"1","createdTime":"2018-01-27 00:00:00","typeId":"3"}]
     */

    private PhysicalDiagnosisEntityBean physicalDiagnosisEntity;
    private DiagnosticPrescriptionEntityBean diagnosticPrescriptionEntity;
    private List<RecipesMotionEntitiesBean> recipesMotionEntities;
    private List<SportsMotionEntitiesBean> sportsMotionEntities;
    private List<SupplementaryKnowledgeEntitiesBean> supplementaryKnowledgeEntities;

    public PhysicalDiagnosisEntityBean getPhysicalDiagnosisEntity() {
        return physicalDiagnosisEntity;
    }

    public void setPhysicalDiagnosisEntity(PhysicalDiagnosisEntityBean physicalDiagnosisEntity) {
        this.physicalDiagnosisEntity = physicalDiagnosisEntity;
    }

    public DiagnosticPrescriptionEntityBean getDiagnosticPrescriptionEntity() {
        return diagnosticPrescriptionEntity;
    }

    public void setDiagnosticPrescriptionEntity(DiagnosticPrescriptionEntityBean diagnosticPrescriptionEntity) {
        this.diagnosticPrescriptionEntity = diagnosticPrescriptionEntity;
    }

    public List<RecipesMotionEntitiesBean> getRecipesMotionEntities() {
        return recipesMotionEntities;
    }

    public void setRecipesMotionEntities(List<RecipesMotionEntitiesBean> recipesMotionEntities) {
        this.recipesMotionEntities = recipesMotionEntities;
    }

    public List<SportsMotionEntitiesBean> getSportsMotionEntities() {
        return sportsMotionEntities;
    }

    public void setSportsMotionEntities(List<SportsMotionEntitiesBean> sportsMotionEntities) {
        this.sportsMotionEntities = sportsMotionEntities;
    }

    public List<SupplementaryKnowledgeEntitiesBean> getSupplementaryKnowledgeEntities() {
        return supplementaryKnowledgeEntities;
    }

    public void setSupplementaryKnowledgeEntities(List<SupplementaryKnowledgeEntitiesBean> supplementaryKnowledgeEntities) {
        this.supplementaryKnowledgeEntities = supplementaryKnowledgeEntities;
    }

    public static class PhysicalDiagnosisEntityBean {
        /**
         * id : 3
         * physicalName : 健康状态良好，体重过重，心肺功能差
         * physicalCode : WEIGHT_BAD
         * typeId : 3
         * physicalContent : 健康状态良好，体重过重，心肺功能差
         */

        private int id;
        private String physicalName;
        private String physicalCode;
        private String typeId;
        private String physicalContent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhysicalName() {
            return physicalName;
        }

        public void setPhysicalName(String physicalName) {
            this.physicalName = physicalName;
        }

        public String getPhysicalCode() {
            return physicalCode;
        }

        public void setPhysicalCode(String physicalCode) {
            this.physicalCode = physicalCode;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getPhysicalContent() {
            return physicalContent;
        }

        public void setPhysicalContent(String physicalContent) {
            this.physicalContent = physicalContent;
        }
    }

    public static class DiagnosticPrescriptionEntityBean {
        /**
         * id : 3
         * diagnosticId : 3
         * diagnosticPurpose : 增重和锻炼
         * sportsIds : 1,2,3
         * recipesIds : 1,2,3,4
         * diagnosticContent : 运动项目：乒乓球，篮球 运动12周，每次运动40到60分钟
         */

        private int id;
        private int diagnosticId;
        private String diagnosticPurpose;
        private String sportsIds;
        private String recipesIds;
        private String diagnosticContent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDiagnosticId() {
            return diagnosticId;
        }

        public void setDiagnosticId(int diagnosticId) {
            this.diagnosticId = diagnosticId;
        }

        public String getDiagnosticPurpose() {
            return diagnosticPurpose;
        }

        public void setDiagnosticPurpose(String diagnosticPurpose) {
            this.diagnosticPurpose = diagnosticPurpose;
        }

        public String getSportsIds() {
            return sportsIds;
        }

        public void setSportsIds(String sportsIds) {
            this.sportsIds = sportsIds;
        }

        public String getRecipesIds() {
            return recipesIds;
        }

        public void setRecipesIds(String recipesIds) {
            this.recipesIds = recipesIds;
        }

        public String getDiagnosticContent() {
            return diagnosticContent;
        }

        public void setDiagnosticContent(String diagnosticContent) {
            this.diagnosticContent = diagnosticContent;
        }
    }

    public static class RecipesMotionEntitiesBean {
        /**
         * id : 3
         * recipesName : 火腿肌肉饭
         * type : 1
         * mealType : 1
         * recipesType : 2
         * ageMix : 11
         * ageMax : 13
         * foodValue : 高蛋白，氨基酸
         * recipesContent : 富含人体吸收元素
         * image : null
         */

        private int id;
        private String recipesName;
        private String type;
        private String mealType;
        private String recipesType;
        private String ageMix;
        private String ageMax;
        private String foodValue;
        private String recipesContent;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRecipesName() {
            return recipesName;
        }

        public void setRecipesName(String recipesName) {
            this.recipesName = recipesName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public String getRecipesType() {
            return recipesType;
        }

        public void setRecipesType(String recipesType) {
            this.recipesType = recipesType;
        }

        public String getAgeMix() {
            return ageMix;
        }

        public void setAgeMix(String ageMix) {
            this.ageMix = ageMix;
        }

        public String getAgeMax() {
            return ageMax;
        }

        public void setAgeMax(String ageMax) {
            this.ageMax = ageMax;
        }

        public String getFoodValue() {
            return foodValue;
        }

        public void setFoodValue(String foodValue) {
            this.foodValue = foodValue;
        }

        public String getRecipesContent() {
            return recipesContent;
        }

        public void setRecipesContent(String recipesContent) {
            this.recipesContent = recipesContent;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public static class SportsMotionEntitiesBean {
        /**
         * id : 1
         * sportsName : 跳绳
         * sportsContent : 双手持绳2端连续跳
         * sportsTime : 30
         * sportsDifficulty : 2
         */

        private int id;
        private String sportsName;
        private String sportsContent;
        private String sportsTime;
        private String sportsDifficulty;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSportsName() {
            return sportsName;
        }

        public void setSportsName(String sportsName) {
            this.sportsName = sportsName;
        }

        public String getSportsContent() {
            return sportsContent;
        }

        public void setSportsContent(String sportsContent) {
            this.sportsContent = sportsContent;
        }

        public String getSportsTime() {
            return sportsTime;
        }

        public void setSportsTime(String sportsTime) {
            this.sportsTime = sportsTime;
        }

        public String getSportsDifficulty() {
            return sportsDifficulty;
        }

        public void setSportsDifficulty(String sportsDifficulty) {
            this.sportsDifficulty = sportsDifficulty;
        }
    }

    public static class SupplementaryKnowledgeEntitiesBean {
        /**
         * id : 3
         * supplementaryName : 形体训练3
         * supplementaryContent : 形体训练队学生的健康有好处
         * image : null
         * status : 1
         * createdTime : 2018-01-27 00:00:00
         * typeId : 3
         */

        private int id;
        private String supplementaryName;
        private String supplementaryContent;
        private String image;
        private String status;
        private String createdTime;
        private String typeId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSupplementaryName() {
            return supplementaryName;
        }

        public void setSupplementaryName(String supplementaryName) {
            this.supplementaryName = supplementaryName;
        }

        public String getSupplementaryContent() {
            return supplementaryContent;
        }

        public void setSupplementaryContent(String supplementaryContent) {
            this.supplementaryContent = supplementaryContent;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }
    }
}
