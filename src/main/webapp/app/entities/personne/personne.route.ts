import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Personne } from 'app/shared/model/personne.model';
import { PersonneService } from './personne.service';
import { PersonneComponent } from './personne.component';
import { PersonneDetailComponent } from './personne-detail.component';
import { PersonneUpdateComponent } from './personne-update.component';
import { PersonneDeletePopupComponent } from './personne-delete-dialog.component';
import { IPersonne } from 'app/shared/model/personne.model';

@Injectable({ providedIn: 'root' })
export class PersonneResolve implements Resolve<IPersonne> {
    constructor(private service: PersonneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((personne: HttpResponse<Personne>) => personne.body));
        }
        return of(new Personne());
    }
}

export const personneRoute: Routes = [
    {
        path: 'personne',
        component: PersonneComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Personnes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'personne/:id/view',
        component: PersonneDetailComponent,
        resolve: {
            personne: PersonneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personnes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'personne/new',
        component: PersonneUpdateComponent,
        resolve: {
            personne: PersonneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personnes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'personne/:id/edit',
        component: PersonneUpdateComponent,
        resolve: {
            personne: PersonneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personnes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const personnePopupRoute: Routes = [
    {
        path: 'personne/:id/delete',
        component: PersonneDeletePopupComponent,
        resolve: {
            personne: PersonneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Personnes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
