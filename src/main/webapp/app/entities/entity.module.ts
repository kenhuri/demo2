import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Demo2PersonneModule } from './personne/personne.module';
import { Demo2BureauModule } from './bureau/bureau.module';
import { Demo2PosteModule } from './poste/poste.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Demo2PersonneModule,
        Demo2BureauModule,
        Demo2PosteModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Demo2EntityModule {}
