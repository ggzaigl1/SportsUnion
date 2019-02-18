package com.fy.baselibrary.entity;

import java.util.List;

/**
 * Created by QKun on 2018/1/30.
 */

public class PhysicalDiagnosisBean {

    private List<PhysicalDiagnosisEntityBean> physicalDiagnosisEntity;

    public List<PhysicalDiagnosisEntityBean> getPhysicalDiagnosisEntity() {
        return physicalDiagnosisEntity;
    }

    public void setPhysicalDiagnosisEntity(List<PhysicalDiagnosisEntityBean> physicalDiagnosisEntity) {
        this.physicalDiagnosisEntity = physicalDiagnosisEntity;
    }

    public static class PhysicalDiagnosisEntityBean {
        /**
         * diagnosticPrescriptionEntity : {"diagnosticContent":"运动项目：乒乓球，篮球 运动12周，每次运动40到60分钟","diagnosticId":3,"diagnosticPurpose":"增重和锻炼","id":3}
         * diagnosticType : 1
         * id : 3
         * physicalCode : WEIGHT_BAD
         * physicalContent : 健康状态良好，体重过重，心肺功能差
         * physicalName : 健康状态良好，体重过重，心肺功能差
         * typeId : 3
         */

        private DiagnosticPrescriptionEntityBean diagnosticPrescriptionEntity;
        private String diagnosticType;
        private int id;
        private String physicalCode;
        private String physicalContent;
        private String physicalName;
        private String typeId;

        public DiagnosticPrescriptionEntityBean getDiagnosticPrescriptionEntity() {
            return diagnosticPrescriptionEntity;
        }

        public void setDiagnosticPrescriptionEntity(DiagnosticPrescriptionEntityBean diagnosticPrescriptionEntity) {
            this.diagnosticPrescriptionEntity = diagnosticPrescriptionEntity;
        }

        public String getDiagnosticType() {
            return diagnosticType;
        }

        public void setDiagnosticType(String diagnosticType) {
            this.diagnosticType = diagnosticType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhysicalCode() {
            return physicalCode;
        }

        public void setPhysicalCode(String physicalCode) {
            this.physicalCode = physicalCode;
        }

        public String getPhysicalContent() {
            return physicalContent;
        }

        public void setPhysicalContent(String physicalContent) {
            this.physicalContent = physicalContent;
        }

        public String getPhysicalName() {
            return physicalName;
        }

        public void setPhysicalName(String physicalName) {
            this.physicalName = physicalName;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public static class DiagnosticPrescriptionEntityBean {
            /**
             * diagnosticContent : 运动项目：乒乓球，篮球 运动12周，每次运动40到60分钟
             * diagnosticId : 3
             * diagnosticPurpose : 增重和锻炼
             * id : 3
             */

            private String diagnosticContent;
            private int diagnosticId;
            private String diagnosticPurpose;
            private int id;

            public String getDiagnosticContent() {
                return diagnosticContent;
            }

            public void setDiagnosticContent(String diagnosticContent) {
                this.diagnosticContent = diagnosticContent;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
