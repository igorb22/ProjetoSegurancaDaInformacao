package com.example.segurados.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

//CLASSE PARA CASO UM NOVO ATRIBUTO SEJA ADICIONADO
public class MigrationRealm implements RealmMigration {
    public static final Long VERSION = 1L;

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            //schema.get("OBJETO").addField("NOMECAMPO", ClasseDoTipo.class);
            oldVersion++;
        }
    }
}
