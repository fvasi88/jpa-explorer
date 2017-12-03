package eu.fvsware.jpaexplorer.dto;

import javax.persistence.metamodel.Attribute;

public class AttributeInfo {
    private String name;
    private String declaringType;
    private String memberName;
    private String javaTypeName;
    private String persistentAttributeType;
    private boolean isAssociation;
    private boolean isCollection;


    public String getName() {
        return name;
    }

    public String getDeclaringType() {
        return declaringType;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getJavaTypeName() {
        return javaTypeName;
    }

    public String getPersistentAttributeType() {
        return persistentAttributeType;
    }

    public boolean isAssociation() {
        return isAssociation;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public static AttributeInfo fromAttributeType(Attribute attribute) {
        AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfo.declaringType = attribute.getDeclaringType().getJavaType().getSimpleName();
        attributeInfo.memberName = attribute.getJavaMember().getName();
        attributeInfo.javaTypeName = attribute.getJavaType().getName();
        attributeInfo.name = attribute.getName();
        attributeInfo.persistentAttributeType = attribute.getPersistentAttributeType().name().toLowerCase();
        attributeInfo.isAssociation = attribute.isAssociation();
        attributeInfo.isCollection = attribute.isCollection();

        return attributeInfo;
    }
}
