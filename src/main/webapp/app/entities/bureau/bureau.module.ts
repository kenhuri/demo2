import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Demo2SharedModule } from 'app/shared';
import {
    BureauComponent,
    BureauDetailComponent,
    BureauUpdateComponent,
    BureauDeletePopupComponent,
    BureauDeleteDialogComponent,
    bureauRoute,
    bureauPopupRoute
} from './';

const ENTITY_STATES = [...bureauRoute, ...bureauPopupRoute];

@NgModule({
    imports: [Demo2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [BureauComponent, BureauDetailComponent, BureauUpdateComponent, BureauDeleteDialogComponent, BureauDeletePopupComponent],
    entryComponents: [BureauComponent, BureauUpdateComponent, BureauDeleteDialogComponent, BureauDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Demo2BureauModule {}
