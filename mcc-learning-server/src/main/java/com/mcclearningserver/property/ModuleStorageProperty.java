/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcclearningserver.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author ACER
 */
@ConfigurationProperties(prefix = "module")
public class ModuleStorageProperty extends FileStorageProperty{
    
}
